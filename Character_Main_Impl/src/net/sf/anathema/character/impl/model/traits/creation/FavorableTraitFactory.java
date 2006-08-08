package net.sf.anathema.character.impl.model.traits.creation;

import net.sf.anathema.character.generic.IBasicCharacterData;
import net.sf.anathema.character.generic.additionalrules.IAdditionalTraitRules;
import net.sf.anathema.character.generic.caste.ICasteType;
import net.sf.anathema.character.generic.caste.ICasteTypeVisitor;
import net.sf.anathema.character.generic.framework.additionaltemplate.model.ICharacterListening;
import net.sf.anathema.character.generic.framework.additionaltemplate.model.ITraitContext;
import net.sf.anathema.character.generic.template.ITraitTemplateCollection;
import net.sf.anathema.character.generic.traits.ITraitTemplate;
import net.sf.anathema.character.generic.traits.ITraitType;
import net.sf.anathema.character.generic.traits.types.AbilityType;
import net.sf.anathema.character.library.trait.DefaultTrait;
import net.sf.anathema.character.library.trait.IValueChangeChecker;
import net.sf.anathema.character.library.trait.aggregated.AggregatedTrait;
import net.sf.anathema.character.library.trait.favorable.IFavorableTrait;
import net.sf.anathema.character.library.trait.favorable.IIncrementChecker;
import net.sf.anathema.character.library.trait.rules.FavorableTraitRules;

public class FavorableTraitFactory extends AbstractTraitFactory {

  private final ITraitContext traitContext;
  private final ITraitTemplateCollection templateCollection;
  private final IBasicCharacterData basicCharacterData;
  private final ICharacterListening characterListening;

  public FavorableTraitFactory(
      ITraitContext traitContext,
      ITraitTemplateCollection templateCollection,
      IAdditionalTraitRules additionalRules,
      IBasicCharacterData basicCharacterData,
      ICharacterListening characterListening) {
    super(traitContext, additionalRules);
    this.traitContext = traitContext;
    this.templateCollection = templateCollection;
    this.basicCharacterData = basicCharacterData;
    this.characterListening = characterListening;
  }

  public IFavorableTrait[] createTraits(
      ITraitType[] traitTypes,
      ICasteType< ? extends ICasteTypeVisitor> casteType,
      IIncrementChecker favoredIncrementChecker) {
    IFavorableTrait[] newTraits = new IFavorableTrait[traitTypes.length];
    for (int index = 0; index < newTraits.length; index++) {
      newTraits[index] = createTrait(traitTypes[index], casteType, favoredIncrementChecker);
    }
    return newTraits;
  }

  public IFavorableTrait createTrait(
      ITraitType traitType,
      ICasteType< ? extends ICasteTypeVisitor> casteType,
      IIncrementChecker favoredIncrementChecker) {
    ITraitTemplate traitTemplate = templateCollection.getTraitTemplate(traitType);
    FavorableTraitRules favorableTraitRules = new FavorableTraitRules(
        traitType,
        traitTemplate,
        traitContext.getLimitationContext());
    IValueChangeChecker valueChecker = createValueIncrementChecker(traitType);
    if (traitType == AbilityType.Craft) {
      String[] elements = new String[] { "Air", "Earth", "Fire", "Water", "Wood" };
      return new AggregatedTrait(favorableTraitRules, traitContext.getTraitValueStrategy(), valueChecker, elements);
    }
    return new DefaultTrait(
        favorableTraitRules,
        casteType,
        traitContext.getTraitValueStrategy(),
        basicCharacterData,
        characterListening,
        valueChecker,
        favoredIncrementChecker);
  }
}