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
package org.polarsys.capella.test.diagram.tools.ju.idb;

import org.eclipse.sirius.business.api.session.Session;
import org.polarsys.capella.core.data.fa.FaPackage;
import org.polarsys.capella.core.data.information.InformationPackage;
import org.polarsys.capella.core.sirius.analysis.IDiagramNameConstants;
import org.polarsys.capella.test.diagram.common.ju.context.IDBDiagram;
import org.polarsys.capella.test.framework.context.SessionContext;
import org.polarsys.capella.test.framework.model.GenericModel;

public class CreateRequires extends IDBProject {

  @Override
  public void test() throws Exception {
    Session session = getSession(getRequiredTestModel());
    SessionContext context = new SessionContext(session);
    init(context);
    testOnDiagram(context, IDiagramNameConstants.INTERFACES_BLANK_DIAGRAM_NAME, LA__LOGICAL_SYSTEM);
    testOnDiagram(context, IDiagramNameConstants.CONTEXTUAL_COMPONENT_EXTERNAL_INTERFACES_DIAGRAM_NAME,
        componentContext);
    testOnDiagram(context, IDiagramNameConstants.CONTEXTUAL_COMPONENT_INTERNAL_INTERFACES_DIAGRAM_NAME,
        componentContext);
  }

  @Override
  protected void testOnDiagram(SessionContext context, String diagramKind, String targetId) {
    IDBDiagram idb = IDBDiagram.createDiagram(context, diagramKind, targetId);

    String component1 = idb.createComponent();
    String interface1 = idb.createInterface();

    idb.createRequires(component1, interface1, GenericModel.INTERFACE_REQUIRE_1,
        GenericModel.COMPONENT_PORT_1);

    idb.mustBeInstanceOf(GenericModel.COMPONENT_PORT_1, FaPackage.Literals.COMPONENT_PORT);

    idb.mustBeLinkedTo(GenericModel.COMPONENT_PORT_1, interface1,
        InformationPackage.Literals.PORT__REQUIRED_INTERFACES);

    idb.createRequiresNotEnabled(GenericModel.COMPONENT_PORT_1, interface1);
  }
}
