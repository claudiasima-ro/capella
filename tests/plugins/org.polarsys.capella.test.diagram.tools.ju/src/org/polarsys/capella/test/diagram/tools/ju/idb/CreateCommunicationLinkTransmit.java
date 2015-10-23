/*******************************************************************************
 * Copyright (c) 2006, 2015 THALES GLOBAL SERVICES.
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
import org.polarsys.capella.core.data.information.communication.CommunicationLink;
import org.polarsys.capella.core.data.information.communication.CommunicationLinkKind;
import org.polarsys.capella.core.data.information.communication.CommunicationPackage;
import org.polarsys.capella.test.diagram.common.ju.context.IDBDiagram;
import org.polarsys.capella.test.diagram.common.ju.context.SessionContext;
import org.polarsys.capella.test.diagram.tools.ju.model.EmptyProject;
import org.polarsys.capella.test.diagram.tools.ju.model.GenericModel;

public class CreateCommunicationLinkTransmit extends EmptyProject {

  @Override
  public void test() throws Exception {
    Session session = getSession(getRequiredTestModel());
    SessionContext context = new SessionContext(session);

    IDBDiagram idb = IDBDiagram.createDiagram(context, LA__LOGICAL_SYSTEM);

    idb.createComponent(GenericModel.LC_1);
    idb.createEvent(GenericModel.EXCHANGE_ITEM_1);
    idb.createCommunicationLinkTransmit(GenericModel.LC_1, GenericModel.EXCHANGE_ITEM_1, GenericModel.CL_1);

    idb.mustBeInstanceOf(GenericModel.CL_1, CommunicationPackage.Literals.COMMUNICATION_LINK);

    CommunicationLink link = context.getSemanticElement(GenericModel.CL_1);
    assertTrue(link.getKind() == CommunicationLinkKind.SEND);
  }
}
