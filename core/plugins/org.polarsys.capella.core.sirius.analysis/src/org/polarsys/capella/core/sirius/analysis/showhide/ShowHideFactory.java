/*******************************************************************************
 * Copyright (c) 2006, 2016 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *  
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.core.sirius.analysis.showhide;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.polarsys.capella.core.data.cs.CsPackage;
import org.polarsys.capella.core.data.ctx.CtxPackage;
import org.polarsys.capella.core.data.interaction.InteractionPackage;
import org.polarsys.capella.core.sirius.analysis.DDiagramContents;

/**
 */
public class ShowHideFactory {

  @Deprecated
  public static AbstractShowHide createShowHide(String eClass, EObject target, DDiagramContents content) {
    return null;
  }

  public static AbstractShowHide createShowHide(EClass eClass, EObject target, DDiagramContents content) {

    if (InteractionPackage.Literals.ABSTRACT_CAPABILITY.isSuperTypeOf(eClass)) {
      return GenericShowHide.createGenericShowHide(InteractionPackage.Literals.ABSTRACT_CAPABILITY, content);
    }
    if (CtxPackage.Literals.MISSION.isSuperTypeOf(eClass)) {
      return GenericShowHide.createGenericShowHide(CtxPackage.Literals.MISSION, content);
    }
    if (CsPackage.Literals.COMPONENT.isSuperTypeOf(eClass)) {
      return new ShowHideMCComponent(content);
    }
    return null;
  }
}
