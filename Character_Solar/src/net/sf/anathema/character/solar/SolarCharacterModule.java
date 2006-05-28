package net.sf.anathema.character.solar;

import net.sf.anathema.character.generic.framework.ICharacterGenerics;
import net.sf.anathema.character.generic.framework.additionaltemplate.IAdditionalViewFactory;
import net.sf.anathema.character.generic.framework.additionaltemplate.model.IAdditionalModelFactory;
import net.sf.anathema.character.generic.framework.additionaltemplate.persistence.IAdditionalPersisterFactory;
import net.sf.anathema.character.generic.framework.module.NullObjectCharacterModuleAdapter;
import net.sf.anathema.character.generic.impl.caste.CasteCollection;
import net.sf.anathema.character.generic.impl.rules.ExaltedEdition;
import net.sf.anathema.character.generic.magic.charms.special.ISpecialCharm;
import net.sf.anathema.character.generic.type.CharacterType;
import net.sf.anathema.character.reporting.CharacterReportingModule;
import net.sf.anathema.character.reporting.CharacterReportingModuleObject;
import net.sf.anathema.character.reporting.sheet.SecondEditionEncodingRegistry;
import net.sf.anathema.character.solar.caste.SolarCaste;
import net.sf.anathema.character.solar.reporting.PdfSolarVirtueFlawEncoder;
import net.sf.anathema.character.solar.reporting.SolarVoidStateReportTemplate;
import net.sf.anathema.character.solar.template.ISolarSpecialCharms;
import net.sf.anathema.character.solar.virtueflaw.SolarVirtueFlawModelFactory;
import net.sf.anathema.character.solar.virtueflaw.SolarVirtueFlawPersisterFactory;
import net.sf.anathema.character.solar.virtueflaw.SolarVirtueFlawTemplate;
import net.sf.anathema.character.solar.virtueflaw.SolarVirtueFlawViewFactory;
import net.sf.anathema.lib.registry.IRegistry;
import net.sf.anathema.lib.resources.IResources;

public class SolarCharacterModule extends NullObjectCharacterModuleAdapter {

  @Override
  public void registerCommonData(ICharacterGenerics characterGenerics) {
    characterGenerics.getCharmProvider().setSpecialCharms(
        CharacterType.SOLAR,
        ExaltedEdition.FirstEdition,
        new ISpecialCharm[] { ISolarSpecialCharms.OX_BODY_TECHNIQUE });
    characterGenerics.getCharmProvider().setSpecialCharms(
        CharacterType.SOLAR,
        ExaltedEdition.SecondEdition,
        new ISpecialCharm[] {
            ISolarSpecialCharms.OX_BODY_TECHNIQUE_SECOND_EDITION,
            ISolarSpecialCharms.GLORIOUS_SOLAR_SABER,
            ISolarSpecialCharms.CITY_MOVING_SECRETS,
            ISolarSpecialCharms.IMMANENT_SOLAR_GLORY,
            ISolarSpecialCharms.RIGHTEOUS_LION_DEFENSE,
            ISolarSpecialCharms.ESSENCE_ARROW_ATTACK });
    characterGenerics.getAdditionalTemplateParserRegistry().register(
        SolarVirtueFlawTemplate.ID,
        new SolarVirtueFlawParser());
    characterGenerics.getCasteCollectionRegistry().register(
        CharacterType.SOLAR,
        new CasteCollection(SolarCaste.values()));
  }

  @Override
  public void addCharacterTemplates(ICharacterGenerics characterGenerics) {
    registerParsedTemplate(characterGenerics, "template/Solar.template"); //$NON-NLS-1$
    registerParsedTemplate(characterGenerics, "template/Solar2nd.template"); //$NON-NLS-1$
  }

  @Override
  public void addReportTemplates(ICharacterGenerics generics, IResources resources) {
    CharacterReportingModuleObject moduleObject = generics.getModuleObjectMap().getModuleObject(CharacterReportingModule.class);
    SecondEditionEncodingRegistry registry = moduleObject.getSecondEditionEncodingRegistry();
    registry.setGreatCurseEncoder(CharacterType.SOLAR, new PdfSolarVirtueFlawEncoder(resources, registry.getBaseFont()));
    generics.getReportTemplateRegistry().add(new SolarVoidStateReportTemplate(resources));
  }

  @Override
  public void addAdditionalTemplateData(ICharacterGenerics characterGenerics) {
    IRegistry<String, IAdditionalModelFactory> additionalModelFactoryRegistry = characterGenerics.getAdditionalModelFactoryRegistry();
    String templateId = SolarVirtueFlawTemplate.ID;
    additionalModelFactoryRegistry.register(templateId, new SolarVirtueFlawModelFactory());
    IRegistry<String, IAdditionalViewFactory> additionalViewFactoryRegistry = characterGenerics.getAdditionalViewFactoryRegistry();
    additionalViewFactoryRegistry.register(templateId, new SolarVirtueFlawViewFactory());
    IRegistry<String, IAdditionalPersisterFactory> persisterFactory = characterGenerics.getAdditonalPersisterFactoryRegistry();
    persisterFactory.register(templateId, new SolarVirtueFlawPersisterFactory());
  }
}