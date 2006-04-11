package net.sf.anathema.charmtree;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.ListCellRenderer;
import javax.swing.border.TitledBorder;

import net.disy.commons.swing.layout.grid.GridDialogLayout;
import net.sf.anathema.character.generic.impl.rules.ExaltedEdition;
import net.sf.anathema.charmtree.presenter.view.ICascadeSelectionView;
import net.sf.anathema.charmtree.presenter.view.ICharmGroupChangeListener;
import net.sf.anathema.charmtree.presenter.view.ICharmTreeView;
import net.sf.anathema.charmtree.presenter.view.ICharmTreeViewProperties;
import net.sf.anathema.charmtree.presenter.view.IDocumentLoadedListener;
import net.sf.anathema.charmtree.presenter.view.IExaltTypeChangedListener;
import net.sf.anathema.lib.control.objectvalue.IObjectValueChangedListener;
import net.sf.anathema.lib.gui.widgets.ChangeableJComboBox;
import net.sf.anathema.lib.gui.widgets.IChangeableJComboBox;
import net.sf.anathema.lib.util.IIdentificate;

public abstract class AbstractCascadeSelectionView implements ICascadeSelectionView {

  private IChangeableJComboBox<IIdentificate> groupComboBox;
  private IChangeableJComboBox<IIdentificate> typeComboBox;
  private final JPanel selectionPanel;
  private final ICharmTreeView charmTreeView;

  public AbstractCascadeSelectionView(ICharmTreeViewProperties treeProperties) {
    this.selectionPanel = new JPanel(new GridDialogLayout(2, false));
    this.charmTreeView = new CharmTreeView(treeProperties);
  }

  public void addCharmTypeSelector(String title, IIdentificate[] types, ListCellRenderer renderer) {
    final JPanel panel = new JPanel(new BorderLayout());
    panel.setBorder(new TitledBorder(title));
    typeComboBox = new ChangeableJComboBox<IIdentificate>(types, false);
    typeComboBox.setSelectedObject(null);
    typeComboBox.setRenderer(renderer);
    panel.add(typeComboBox.getComponent(), BorderLayout.CENTER);
    getSelectionComponent().add(panel);
  }

  public void fillCharmGroupBox(IIdentificate[] charmGroups) {
    groupComboBox.setObjects(charmGroups);
  }

  public void addCharmTypeSelectionListener(final IExaltTypeChangedListener selectionListener) {
    typeComboBox.addObjectSelectionChangedListener(new IObjectValueChangedListener<IIdentificate>() {
      public void valueChanged(IIdentificate newValue) {
        selectionListener.valueChanged(newValue);
      }
    });
  }

  public void addCharmGroupSelector(
      String title,
      ListCellRenderer renderer,
      final ICharmGroupChangeListener selectionListener,
      Dimension preferredSize) {
    final JPanel panel = new JPanel(new BorderLayout());
    panel.setBorder(new TitledBorder(title));
    groupComboBox = new ChangeableJComboBox<IIdentificate>(null, false);
    groupComboBox.setSelectedObject(null);
    groupComboBox.setRenderer(renderer);
    groupComboBox.setPreferredSize(preferredSize);
    groupComboBox.addObjectSelectionChangedListener(new IObjectValueChangedListener() {
      public void valueChanged(Object newValue) {
        selectionListener.valueChanged(
            groupComboBox.getSelectedObject(),
            typeComboBox.getSelectedObject(),
            ExaltedEdition.FirstEdition);
      }
    });
    panel.add(groupComboBox.getComponent(), BorderLayout.CENTER);
    getSelectionComponent().add(panel);
  }

  protected final JComponent getSelectionComponent() {
    return selectionPanel;
  }

  public final ICharmTreeView getCharmTreeView() {
    return charmTreeView;
  }

  public final void addDocumentLoadedListener(IDocumentLoadedListener documentListener) {
    charmTreeView.addDocumentLoadedListener(documentListener);
  }

  public final void dispose() {
    charmTreeView.dispose();
  }

  protected IChangeableJComboBox<IIdentificate> getTypeComboBox() {
    return typeComboBox;
  }

  public void fillCharmComboBoxes(IIdentificate charmType, IIdentificate charmGroup) {
    typeComboBox.setSelectedObject(charmType);
    groupComboBox.setSelectedObject(charmGroup);
  }
}