package net.sf.anathema.character.presenter.magic.charm;

import net.sf.anathema.character.generic.magic.charms.special.IOxBodyTechniqueCharm;
import net.sf.anathema.framework.value.IIntValueView;
import net.sf.anathema.platform.svgtree.presenter.view.CategorizedSpecialNodeView;
import net.sf.anathema.platform.svgtree.presenter.view.ISpecialNodeView;
import net.sf.anathema.platform.svgtree.presenter.view.SpecialCharmContainer;

import java.util.ArrayList;
import java.util.List;

public class SwingCategorizedSpecialView implements CategorizedSpecialNodeView, ISpecialNodeView {
  private final IOxBodyTechniqueCharm visitedCharm;
  private final List<ProxyIntValueView> views = new ArrayList<ProxyIntValueView>();

  public SwingCategorizedSpecialView(IOxBodyTechniqueCharm visitedCharm) {
    this.visitedCharm = visitedCharm;
  }

  @Override
  public IIntValueView addCategory(String labelText, int maxValue, int value) {
    ProxyIntValueView proxyIntValueView = new ProxyIntValueView(labelText, maxValue, value);
    views.add(proxyIntValueView);
    return proxyIntValueView;
  }

  @Override
  public String getNodeId() {
    return visitedCharm.getCharmId();
  }

  @Override
  public void showIn(SpecialCharmContainer container) {
    for (ProxyIntValueView view : views) {
      IIntValueView actualView = container.add(IIntValueView.class, view.getLabel(), view.getValue(), view.getMaxValue());
      view.setActualView(actualView);
    }
  }

  @Override
  public void hide() {
    //To change body of implemented methods use File | Settings | File Templates.
  }

  @Override
  public void reset() {
    //To change body of implemented methods use File | Settings | File Templates.
  }
}