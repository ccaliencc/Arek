package arek.nauka.messenger.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import arek.nauka.messenger.database.DatabaseClass;
import arek.nauka.messenger.model.Profile;

public class ProfileService
{
	private Map<String, Profile> profiles = DatabaseClass.getProfiles();
	
	public ProfileService()
	{
		profiles.put("arek", new Profile(1L, "arek", "Arek", "Krawczyk")); // klucz tablicy arek, ID 1L, nazwa uzytkownika arek, imie Arek, nazwisko Krawczyk. Do klucza tablicy jest przypisany adres /arek
	}

	public List<Profile> getAllProfiles()
	{
		return new ArrayList<Profile>(profiles.values());
	}

	public Profile getProfile(String profileName)
	{
		return profiles.get(profileName);
	}

	public Profile addProfile(Profile profile)
	{
		profile.setId(profiles.size() + 1); // powiększenie arraylist o 1
		profiles.put(profile.getProfileName(), profile); // dodanie do listy
		return profile;
	}

	public Profile updateProfile(Profile profile)
	{
		if (profile.getProfileName().isEmpty())
			return null;

		profiles.put(profile.getProfileName(), profile);
		return profile;
	}

	public Profile removeProfile(String profileName)
	{
		return profiles.remove(profileName);
	}

}
