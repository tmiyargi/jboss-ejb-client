/*
 * JBoss, Home of Professional Open Source.
 * Copyright 2011, Red Hat, Inc., and individual contributors
 * as indicated by the @author tags. See the copyright.txt file in the
 * distribution for a full listing of individual contributors.
 *
 * This is free software; you can redistribute it and/or modify it
 * under the terms of the GNU Lesser General Public License as
 * published by the Free Software Foundation; either version 2.1 of
 * the License, or (at your option) any later version.
 *
 * This software is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this software; if not, write to the Free
 * Software Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA
 * 02110-1301 USA, or see the FSF site: http://www.fsf.org.
 */

package org.jboss.ejb.client.remoting;

import java.io.DataOutput;
import java.io.IOException;

/**
 * User: jpai
 */
class SessionOpenRequestWriter extends AbstractMessageWriter {

    private static final byte HEADER_SESSION_OPEN_REQUEST = 0x01;

    private final byte protocolVersion;
    private final String marshallingStrategy;

    SessionOpenRequestWriter(final byte protocolVersion, final String marshallingStrategy) {
        this.protocolVersion = protocolVersion;
        this.marshallingStrategy = marshallingStrategy;
    }

    void writeMessage(final DataOutput output, final short invocationId, final String appName, final String moduleName, final String distinctName, final String beanName, final RemotingAttachments attachments) throws IOException {
        // write the header
        output.writeByte(HEADER_SESSION_OPEN_REQUEST);
        // write the invocation id
        output.writeShort(invocationId);
        // ejb identifier
        if (appName == null) {
            output.writeUTF("");
        } else {
            output.writeUTF(appName);
        }
        output.writeUTF(moduleName);
        if (distinctName == null) {
            output.writeUTF("");
        } else {
            output.writeUTF(distinctName);
        }
        output.writeUTF(beanName);
        // write the attachments
        this.writeAttachments(output, attachments);
    }

}
