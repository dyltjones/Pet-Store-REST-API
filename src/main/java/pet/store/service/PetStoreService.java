package pet.store.service;

import java.util.NoSuchElementException;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pet.store.controller.model.PetStoreData;
import pet.store.dao.PetStoreDao;
import pet.store.entity.PetStore;

@Service
public class PetStoreService {

    @Autowired
    private PetStoreDao petStoreDao;

    public PetStoreData savePetStore(PetStoreData petStoreData) {
	PetStore petStore = findOrCreatePetStore(petStoreData.getPetStoreId());
	setPetStoreFields(petStore, petStoreData);
	petStore = petStoreDao.save(petStore);
	return new PetStoreData(petStore);
    }

    private void setPetStoreFields(PetStore petStore, PetStoreData petStoreData) {
	petStore.setStoreName(petStoreData.getStoreName());
	petStore.setStoreAddress(petStoreData.getStoreAddress());
	petStore.setStoreCity(petStoreData.getStoreCity());
	petStore.setStoreState(petStoreData.getStoreState());
	petStore.setStoreZip(petStoreData.getStoreZip());
	petStore.setStorePhone(petStoreData.getStorePhone());


    }

    private PetStore findOrCreatePetStore(Long petStoreId) {
	PetStore petStore;

	if (Objects.isNull(petStoreId)) {
	    petStore = new PetStore();
	} else {
	    petStore = findPetStoreById(petStoreId);
	}
	return petStore;
    }

    private PetStore findPetStoreById(Long petStoreId) {
	return petStoreDao.findById(petStoreId).orElseThrow(
		() -> new NoSuchElementException("Pet Store with ID=" + petStoreId + " does not exist"));
    }


}
