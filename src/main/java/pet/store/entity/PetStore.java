package pet.store.entity;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;


//pet store with a list of customers and employees
@Entity
@Data
public class PetStore {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long petStoreId;
	
	private String storeName;
	private String storeAddress;
	private String storeCity;
	private String storeState;
	private int storeZip;
	private String storePhone;
	
	
	
	
	@EqualsAndHashCode.Exclude
	@ToString.Exclude
	@OneToMany(mappedBy = "petStore", cascade = CascadeType.ALL,
			orphanRemoval = true)
	private Set<Employee> employees = new HashSet<>();
	
	//creates the pet_store_customer table with pet_store_Id customer_id
	@EqualsAndHashCode.Exclude
	@ToString.Exclude
	@ManyToMany(cascade = CascadeType.PERSIST)
	@JoinTable(name = "pet_store_customer", joinColumns = 
	@JoinColumn(name = "pet_store_id"),inverseJoinColumns = 
	@JoinColumn(name = "customer_id"))
	private Set<Customer> customers = new HashSet<>();
	
	
}
