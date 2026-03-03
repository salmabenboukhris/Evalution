package ma.project.service;

import ma.project.classes.Categorie;

import java.io.Serializable;

public class CategorieService extends AbstractFacade<Categorie> {
	public CategorieService() {
		super(Categorie.class);
	}

	@Override
	public Object findById(Serializable serializable) {
		return null;
	}
}
