package com.reprap.reprapgui.controller;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import com.reprap.reprapgui.model.AbstractModel;
import com.reprap.reprapgui.view.panels.AbstractViewPanel;

/**
 * This class provides base level functionality for each controller. This includes the
 * ability to register multiple models and views, propagating model change events to
 * each of the views, and providing a utility function to broadcast model property
 * changes when necessary.
 */
public abstract class AbstractController implements PropertyChangeListener {
    
    private final List<AbstractViewPanel> registeredViews;
    private final List<AbstractModel> registeredModels;
    
    
    /** Creates a new instance of Controller */
    public AbstractController() {
        registeredViews  = new ArrayList<AbstractViewPanel>();
        registeredModels = new ArrayList<AbstractModel>();
    }

    
    /**
     * Binds a model to this controller. Once added, the controller will listen for all 
     * model property changes and propagate them on to registered views. In addition,
     * it is also responsible for resetting the model properties when a view changes
     * state.
     * @param model The model to be added
     */
    public void setModel(final AbstractModel model) {
        registeredModels.add(model);
        model.addPropertyChangeListener(this);
    }

    /**
     * Unbinds a model from this controller.
     * @param model The model to be removed
     */
    public void removeModel(final AbstractModel model) {
        registeredModels.remove(model);
        model.removePropertyChangeListener(this);
    }
    
    
    /**
     * Binds a view to this controller. The controller will propagate all model property
     * changes to each view for consideration.
     * @param view The view to be added
     */
    public void setView(final AbstractViewPanel view) {
        registeredViews.add(view);
    }

    /**
     * Unbind a view from this controller.
     * @param view The view to be removed
     */
    public void removeView(final AbstractViewPanel view) {
        registeredViews.remove(view);
    }
    
    /**
     * This method is used to implement the PropertyChangeListener interface. Any model
     * changes will be sent to this controller through the use of this method.
     * @param evt An object that describes the model's property change.
     */
    @Override
	public void propertyChange(final PropertyChangeEvent evt) {
        for (final AbstractViewPanel view: registeredViews) {
            view.modelPropertyChange(evt);
        }
    }
    
    
    /**
     * Convenience method that subclasses can call upon to fire off property changes
     * back to the models. This method used reflection to inspect each of the model
     * classes to determine if it is the owner of the property in question. If it
     * isn't, a NoSuchMethodException is throws (which the method ignores).
     *
     * @param propertyName The name of the property
     * @param newValue An object that represents the new value of the property.
     */
    protected void setModelProperty(final String propertyName, final Object newValue) {
        
        for (final AbstractModel model: registeredModels) {
            try {
                
                final Method method = model.getClass().
                    getMethod("set"+propertyName, new Class[] {newValue.getClass()});
                method.invoke(model, newValue);
                
                System.out.println("AbstractController.setModelProperty() " + method.toGenericString() + newValue);
                
            } catch (final Exception ex) {
            }
        }
    }
}
