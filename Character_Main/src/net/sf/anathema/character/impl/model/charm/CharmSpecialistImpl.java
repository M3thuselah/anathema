package net.sf.anathema.character.impl.model.charm;

import net.sf.anathema.character.main.hero.Hero;
import net.sf.anathema.character.main.model.experience.ExperienceModel;
import net.sf.anathema.character.main.model.experience.ExperienceModelFetcher;
import net.sf.anathema.character.main.model.health.HealthModel;
import net.sf.anathema.character.main.model.health.HealthModelFetcher;
import net.sf.anathema.character.main.model.traits.TraitModel;
import net.sf.anathema.character.main.model.traits.TraitModelFetcher;

public class CharmSpecialistImpl implements CharmSpecialist {

  private Hero hero;

  public CharmSpecialistImpl(Hero hero) {
    this.hero = hero;
  }

  @Override
  public TraitModel getTraits() {
    return TraitModelFetcher.fetch(hero);
  }

  @Override
  public ExperienceModel getExperience() {
    return ExperienceModelFetcher.fetch(hero);
  }

  @Override
  public HealthModel getHealth() {
    return HealthModelFetcher.fetch(hero);
  }
}