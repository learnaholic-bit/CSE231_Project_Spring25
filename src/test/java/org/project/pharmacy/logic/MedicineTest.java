package org.project.pharmacy.logic;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;
class MedicineTest {
    Medicine medicine;
    void createMedicine() {
         medicine= new Medicine(101, "Aspirin", 5.99, "Analgesic", "NSAID",
                "Relieves mild to moderate pain and reduces inflammation",
                true, 100, "Acetylsalicylic Acid", "325 mg", false,
                LocalDate.of(2026, 12, 31));
    }

    @Test
    void setandgetCategory() {
        createMedicine();
        medicine.setCategory("Painkiller");
        assertEquals("Painkiller", medicine.getCategory());
    }
    @Test
    void setandgetItemId() {
        createMedicine();
        medicine.setItemId(200);
        assertEquals(200, medicine.getItemId());
    }


    @Test
    void setandgetName() {
        createMedicine();
        medicine.setName("Aspirin2");
        assertEquals("Aspirin2", medicine.getName());
    }
    @Test
    void setandgetPrice() {
        createMedicine();
        medicine.setPrice(8.99);
        assertEquals(8.99, medicine.getPrice());
    }

    @Test
    void setandgetDescription() {
        createMedicine();
        medicine.setDescription("relieves pain");
        assertEquals("relieves pain", medicine.getDescription());
    }
    @Test
    void setandgetQuantity() {
        createMedicine();
        medicine.setQuantity(900);
        assertEquals(900, medicine.getQuantity());
    }
    @Test
    void setandgetAvailable() {
        createMedicine();
        medicine.setAvailable(false);
        assertEquals(false, medicine.getAvailable());
    }

    @Test
    void isSoldOut() {
        createMedicine();
        assertEquals(false, medicine.isSoldOut());
    }

    @Test
    void getExpiryDate() {
        createMedicine();
        assertEquals(LocalDate.of(2026, 12, 31), medicine.getExpiryDate());
    }

    @Test
    void setandgetSubCategory() {
        createMedicine();
        medicine.setSubCategory("antibiotic");
        assertEquals("antibiotic", medicine.getSubCategory());
    }

    @Test
    void getDosage() {
        createMedicine();
        assertEquals("325 mg", medicine.getDosage());
    }

    @Test
    void getRequiresPrescription() {
        createMedicine();
        assertEquals(false, medicine.getRequiresPrescription());
    }

    @Test
    void getActiveIngredient() {
        createMedicine();
        assertEquals("Acetylsalicylic Acid", medicine.getActiveIngredient());
    }

    @Test
    void sellItem1() {
        createMedicine();
        medicine.setQuantity(0);
        assertThrows(IllegalStateException.class, () -> medicine.sellItem());

    }
    @Test
    void sellItem2() {
        createMedicine();

        medicine.sellItem();
        assertEquals(99, medicine.getQuantity());

    }

    @Test
    void isExpired() {
        createMedicine();
        assertEquals(false, medicine.isExpired());
    }

    @Test
    void addQuantity1() {
        createMedicine();
        assertThrows(IllegalArgumentException.class, () -> medicine.addQuantity(-8));

    }
    @Test
    void addQuantity2() {
        createMedicine();
        medicine.setQuantity(100);
        medicine.addQuantity(10);
        assertEquals(110, medicine.getQuantity());
    }

}