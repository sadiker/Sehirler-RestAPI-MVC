package com.sadiker.cities;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
// import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import com.sadiker.cities.model.City;
import com.sadiker.cities.repository.CityRepository;
import com.sadiker.cities.service.CityService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;

@SpringBootApplication
@EnableMongoRepositories(basePackages = "com.sadiker.cities.repository")
@EnableCaching
public class CitiesApplication implements CommandLineRunner {

	@Autowired
	CityRepository cityRepository;

	// @Autowired
	// MongoTemplate mongoTemplate;

	public static void main(String[] args) {
		SpringApplication.run(CitiesApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		if (cityRepository.findAll().size() == 0) {
			City c1 = new City();
			c1.setImage("http://www.istanbul.gov.tr/kurumlar/istanbul.gov.tr/guncel/martturizm.jpg");
			c1.setName("İSTANBUL");
			c1.setPlate("34");
			c1.setRegion("MARMARA BÖLGESİ");
			cityRepository.save(c1);

			City c2 = new City();
			c2.setImage("https://www.kulturportali.gov.tr/contents/images/Sumela1_20170216130744671(1).jpg");
			c2.setName("TRABZON");
			c2.setPlate("61");
			c2.setRegion("KARADENİZ BÖLGESİ");
			cityRepository.save(c2);

			City c3 = new City();
			c3.setImage(
					"http://www.sur.gov.tr/kurumlar/sur.gov.tr/il%C3%A7emiz_fotolar/Sur%20Tarihi%20Foto/ulu_cami_ust.JPG");
			c3.setName("DİYARBAKIR");
			c3.setPlate("21");
			c3.setRegion("GÜNEYDOĞU ANADOLU BÖLGESİ");
			cityRepository.save(c3);

			City c4 = new City();
			c4.setImage("http://www.izmir.gov.tr/kurumlar/izmir.gov.tr/BilgiIslem/sehir_kartlari/turizm/017_izmir.jpg");
			c4.setName("İZMİR");
			c4.setPlate("35");
			c4.setRegion("EGE BÖLGESİ");
			cityRepository.save(c4);

			City c5 = new City();
			c5.setImage("https://www.anitkabir.tsk.tr/content/img/04_gorseller/fotograflar/fotograflar_89.jpg");
			c5.setName("ANKARA");
			c5.setPlate("06");
			c5.setRegion("İÇ ANADOLU BÖLGESİ");
			cityRepository.save(c5);

			City c6 = new City();
			c6.setImage(
					"http://www.antalya.gov.tr/kurumlar/antalya.gov.tr/Site/sehir_kartlari/kaleici/kaleici_yivli_minare_saat_kulesi.JPG");
			c6.setName("ANTALYA");
			c6.setPlate("07");
			c6.setRegion("AKDENİZ BÖLGESİ");
			cityRepository.save(c6);

			City c7 = new City();
			c7.setImage(
					"http://www.erzurum.gov.tr/kurumlar/erzurum.gov.tr/AltPromo/cifteler1.jpg");
			c7.setName("ERZURUM");
			c7.setPlate("25");
			c7.setRegion("DOĞU ANADOLU BÖLGESİ");
			cityRepository.save(c7);
		}

	}

}
