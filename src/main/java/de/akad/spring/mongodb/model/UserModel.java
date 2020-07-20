package de.akad.spring.mongodb.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "users")
public class UserModel {
  @Id
  private String id;

  private String title;
  private String description;
  private String email;
  private String vorname;
  private String nachname;
  private String passwort;
  private String geburtsdatum;
  
  private boolean published;
  
  public String getVorname() {
	return vorname;
}

public String getEmail() {
	return email;
}

public void setEmail(String email) {
	this.email = email;
}

public void setVorname(String vorname) {
	this.vorname = vorname;
}

public String getNachname() {
	return nachname;
}

public void setNachname(String nachname) {
	this.nachname = nachname;
}

public String getPasswort() {
	return passwort;
}

public void setPasswort(String passwort) {
	this.passwort = passwort;
}

public String getGeburtsdatum() {
	return geburtsdatum;
}

public void setGeburtsdatum(String geburtsdatum) {
	this.geburtsdatum = geburtsdatum;
}

public void setId(String id) {
	this.id = id;
}


  public UserModel() {

  }

  public UserModel(String email, String vorname, String nachname, String geburtsdatum, String passwort, String title, String description, boolean published) {
    this.title = title;
    this.description = description;
    this.email = email;
    this.vorname = vorname;
    this.nachname = nachname;
    this.geburtsdatum = geburtsdatum;
    this.passwort = passwort;
    this.published = published;
  }

  public String getId() {
    return id;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public boolean isPublished() {
    return published;
  }

  public void setPublished(boolean isPublished) {
    this.published = isPublished;
  }

  @Override
  public String toString() {
    return "Tutorial [id=" + id + ", email=" + email +  ", vorname=" + vorname +  ", nachname=" + nachname +  ", geburtsdatum=" + geburtsdatum  + ", passwort=" + passwort  + ", title=" + title + ", desc=" + description + ", published=" + published + "]";
  }
}
