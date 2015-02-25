/*******************************************************************************
 * Copyright (c) 2006, 2014 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *  
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.core.transition.system.topdown.ui.preferences;

import org.eclipse.jface.preference.IPreferenceStore;
import org.polarsys.capella.common.flexibility.properties.loader.PropertiesLoader;
import org.polarsys.capella.common.flexibility.properties.property.PropertyContext;
import org.polarsys.capella.common.flexibility.properties.schema.IProperties;
import org.polarsys.capella.common.flexibility.properties.schema.IPropertyContext;
import org.polarsys.capella.common.flexibility.wizards.loader.RenderersLoader;
import org.polarsys.capella.common.flexibility.wizards.renderer.RendererContext;
import org.polarsys.capella.common.flexibility.wizards.schema.IRendererContext;
import org.polarsys.capella.common.flexibility.wizards.schema.IRenderers;
import org.polarsys.capella.core.flexibility.wizards.ui.CapellaPropertyPreferencePage;
import org.polarsys.capella.core.preferences.Activator;
import org.polarsys.capella.core.transition.system.topdown.constants.ITopDownConstants;

/**
 */
public class TopDownPreferencePage extends CapellaPropertyPreferencePage {

  public static final String PROPERTY_PAGE_ID = "org.polarsys.capella.core.projection.preferences.ProjectionPreferencePage"; //$NON-NLS-1$

  protected IProperties _properties = null;
  protected IPropertyContext _context = null;
  protected IRenderers _renderers = null;

  public TopDownPreferencePage() {
    super(PROPERTY_PAGE_ID);
  }

  public IProperties getProperties() {
    if (_properties == null) {
      _properties = new PropertiesLoader().getProperties(ITopDownConstants.OPTIONS_SCOPE__PREFERENCES);
    }

    return _properties;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public IPropertyContext getContext() {
    if (_context == null) {
      _context = new PropertyContext(getProperties()); 
    }
//		{
//		@Override
//		public void writeAll() {
//			super.writeAll();
//			ScopedCapellaPreferencesStore.getInstance(Activator.PLUGIN_ID).save();
//		}
//    	  
//      };
//    }
    return _context;
  }

  /**
   * {@inheritDoc}
   */
  public IRenderers getRenderers() {
    if (_renderers == null) {
      _renderers = new RenderersLoader().getRenderers(getContext().getProperties());
    }
    return _renderers;
  }

  /**
   * @see org.eclipse.jface.preference.PreferencePage#doGetPreferenceStore()
   */
  @Override
  protected IPreferenceStore doGetPreferenceStore() {
    return Activator.getDefault().getPreferenceStore();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public String getPageTitle() {
    return "Transition Preferences";
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public String getPageDescription() {
    return "Transition Preferences";
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public IRendererContext getRendererContext() {
    return new RendererContext(getRenderers(), getContext());
  }
}
