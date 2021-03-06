package net.sf.anathema.hero.charms.model.learn;

import net.sf.anathema.character.magic.basic.Magic;

import java.util.Collection;
import java.util.Set;

public interface MagicLearner {

  boolean handlesMagic(Magic magic);

  int getAdditionalBonusPoints(Magic magic);

  int getCreationLearnCount(Magic magic, Set<Magic> alreadyHandledMagic);

  Collection<? extends Magic > getLearnedMagic(boolean experienced);
}
