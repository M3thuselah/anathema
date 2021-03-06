package net.sf.anathema.character.framework.xml;

import net.sf.anathema.hero.template.ConfiguredModel;
import net.sf.anathema.hero.template.HeroTemplate;
import net.sf.anathema.hero.template.TemplateType;
import net.sf.anathema.hero.template.creation.BonusPointCosts;
import net.sf.anathema.hero.template.creation.ICreationPoints;
import net.sf.anathema.hero.template.experience.IExperiencePointCosts;
import net.sf.anathema.character.framework.xml.creation.GenericBonusPointCosts;
import net.sf.anathema.character.framework.xml.creation.GenericCreationPoints;
import net.sf.anathema.character.framework.xml.experience.GenericExperiencePointCosts;
import net.sf.anathema.lib.exception.UnreachableCodeReachedException;
import net.sf.anathema.lib.lang.clone.ICloneable;

import java.util.ArrayList;
import java.util.List;

public class GenericCharacterTemplate implements HeroTemplate, ICloneable<GenericCharacterTemplate> {

  private TemplateType templateType;
  private GenericExperiencePointCosts experienceCosts = new GenericExperiencePointCosts();
  private GenericBonusPointCosts bonusPointCosts = new GenericBonusPointCosts();
  private GenericCreationPoints creationPoints = new GenericCreationPoints();
  private final List<ConfiguredModel> models = new ArrayList<>();

  @Override
  public BonusPointCosts getBonusPointCosts() {
    return bonusPointCosts;
  }

  @Override
  public ICreationPoints getCreationPoints() {
    return creationPoints;
  }

  @Override
  public IExperiencePointCosts getExperienceCost() {
    return experienceCosts;
  }

  @Override
  public TemplateType getTemplateType() {
    return templateType;
  }

  @Override
  public List<ConfiguredModel> getModels() {
    return new ArrayList<>(models);
  }

  public void setCreationPoints(GenericCreationPoints creationPoints) {
    this.creationPoints = creationPoints;
  }

  public void setBonusPointCosts(GenericBonusPointCosts bonusPoints) {
    this.bonusPointCosts = bonusPoints;
  }

  public void setExperiencePointCosts(GenericExperiencePointCosts experienceCosts) {
    this.experienceCosts = experienceCosts;
  }

  public void setTemplateType(TemplateType templateType) {
    this.templateType = templateType;
  }

  @SuppressWarnings("CloneDoesntDeclareCloneNotSupportedException")
  @Override
  public GenericCharacterTemplate clone() {
    GenericCharacterTemplate clone;
    try {
      clone = (GenericCharacterTemplate) super.clone();
    } catch (CloneNotSupportedException e) {
      throw new UnreachableCodeReachedException(e);
    }
    if (bonusPointCosts != null) {
      clone.bonusPointCosts = bonusPointCosts.clone();
    }
    if (creationPoints != null) {
      clone.creationPoints = creationPoints.clone();
    }
    if (experienceCosts != null) {
      clone.experienceCosts = experienceCosts.clone();
    }
    return clone;
  }

  public void addModel(String modelId, String templateId) {
    models.add(new ConfiguredModel(modelId, templateId));
  }
}
