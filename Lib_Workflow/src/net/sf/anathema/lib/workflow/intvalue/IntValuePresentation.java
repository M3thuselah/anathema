package net.sf.anathema.lib.workflow.intvalue;

import net.sf.anathema.lib.control.intvalue.IIntValueChangedListener;
import net.sf.anathema.lib.gui.widgets.IntegerSpinner;

public class IntValuePresentation {

  public void initView(final IntegerSpinner integerSpinner, final IIntValueModel intValueModel) {
    integerSpinner.setMinimum(new Integer(1));
    integerSpinner.addChangeListener(new IIntValueChangedListener() {
      public void valueChanged(int newValue) {
        intValueModel.setValue(newValue);
      }
    });
    intValueModel.addIntValueChangeListener(new IIntValueChangedListener() {
      public void valueChanged(int newValue) {
        integerSpinner.setValue(newValue);
      }
    });
    integerSpinner.setValue(intValueModel.getValue());
  }
}