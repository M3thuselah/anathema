package net.sf.anathema.hero.combos.display.view;

import javafx.scene.Node;
import javafx.scene.control.ScrollPane;
import net.miginfocom.layout.CC;
import net.sf.anathema.hero.combos.display.presenter.ComboConfigurationView;
import net.sf.anathema.hero.combos.display.presenter.ComboContainer;
import net.sf.anathema.hero.combos.display.presenter.ComboViewProperties;
import net.sf.anathema.hero.magic.display.FxMagicLearnView;
import net.sf.anathema.hero.magic.display.MagicLearnProperties;
import net.sf.anathema.hero.magic.display.MagicLearnView;
import net.sf.anathema.lib.gui.layout.LayoutUtils;
import net.sf.anathema.lib.workflow.textualdescription.ITextView;
import net.sf.anathema.platform.fx.FxTextView;
import net.sf.anathema.platform.fx.FxThreading;
import net.sf.anathema.platform.fx.NodeHolder;
import org.tbee.javafx.scene.layout.MigPane;

import static net.sf.anathema.lib.gui.layout.LayoutUtils.fillWithoutInsets;

public class FxComboConfigurationView implements ComboConfigurationView, NodeHolder {
  private final MigPane editPane = new MigPane(fillWithoutInsets().wrapAfter(2));
  private final ScrollPane content = new ScrollPane();
  private final MigPane namePanel = new MigPane(LayoutUtils.withoutInsets().wrapAfter(1));

  public FxComboConfigurationView() {
    content.setContent(editPane);
  }

  @Override
  public void addComboEditor(ComboViewProperties properties) {
    FxThreading.runOnCorrectThread(new Runnable() {
      @Override
      public void run() {
        editPane.add(namePanel, new CC().alignY("top"));
      }
    });
  }

  @Override
  public MagicLearnView addMagicLearnView(MagicLearnProperties viewProperties) {
    final FxMagicLearnView magicLearnView = new FxMagicLearnView(viewProperties);
    FxThreading.runOnCorrectThread(new Runnable() {
      @Override
      public void run() {
        editPane.add(magicLearnView.getNode());
      }
    });
    return magicLearnView;
  }

  @Override
  public ComboContainer addComboContainer() {
    final FxComboContainer container = new FxComboContainer();
    FxThreading.runOnCorrectThread(new Runnable() {
      @Override
      public void run() {
        editPane.add(container.getNode(), new CC().spanX().grow().push());
      }
    });
    return container;
  }

  @Override
  public Node getNode() {
    return content;
  }

  @Override
  public ITextView addComboNameView(String viewTitle) {
    FxTextView textView = FxTextView.SingleLine(viewTitle);
    return addTextView(textView);
  }

  @Override
  public ITextView addComboDescriptionView(String viewTitle) {
    FxTextView textView = FxTextView.MultiLine(viewTitle);
    return addTextView(textView);
  }

  private ITextView addTextView(final FxTextView textView) {
    FxThreading.runOnCorrectThread(new Runnable() {
      @Override
      public void run() {
        namePanel.add(textView.getNode(), new CC().growX());
      }
    });
    return textView;
  }
}