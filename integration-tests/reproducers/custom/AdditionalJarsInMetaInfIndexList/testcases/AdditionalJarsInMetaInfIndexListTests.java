/* AdditionalJarsInMetaInfIndexListTests.java
Copyright (C) 2011 Red Hat, Inc.

This file is part of IcedTea.

IcedTea is free software; you can redistribute it and/or
modify it under the terms of the GNU General Public License as published by
the Free Software Foundation, version 2.

IcedTea is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
General Public License for more details.

You should have received a copy of the GNU General Public License
along with IcedTea; see the file COPYING.  If not, write to
the Free Software Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA
02110-1301 USA.

Linking this library statically or dynamically with other modules is
making a combined work based on this library.  Thus, the terms and
conditions of the GNU General Public License cover the whole
combination.

As a special exception, the copyright holders of this library give you
permission to link this library with independent modules to produce an
executable, regardless of the license terms of these independent
modules, and to copy and distribute the resulting executable under
terms of your choice, provided that you also meet, for each linked
independent module, the terms and conditions of the license of that
module.  An independent module is a module which is not derived from
or based on this library.  If you modify this library, you may extend
this exception to your version of the library, but you are not
obligated to do so.  If you do not wish to do so, delete this
exception statement from your version.
 */

import net.sourceforge.jnlp.ProcessResult;
import net.sourceforge.jnlp.ServerAccess;
import net.sourceforge.jnlp.annotations.Bug;
import net.sourceforge.jnlp.annotations.KnownToFail;
import net.sourceforge.jnlp.browsertesting.BrowserTest;
import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.*;

public class AdditionalJarsInMetaInfIndexListTests extends BrowserTest {

    private static ServerAccess server = new ServerAccess();
    private static final List<String> TRUSTALL = Collections.unmodifiableList(Arrays.asList(new String[] { "-Xtrustall" }));
    private static final String CORRECT_EXEC = "Program Executed Correctly.";

    @Test
    @KnownToFail
    @Bug(id = "PR1112")
    public void SignedMetaInfIndexListTest() throws Exception {
        ProcessResult pr = server.executeJavawsHeadless("/AdditionalJarsInMetaInfIndexListSigned.jnlp");
        assertTrue("LoadedViaMetaInfIndexList's stdout should contain " + CORRECT_EXEC + " but did not.", pr.stdout.contains(CORRECT_EXEC));
    }

    @Test
    public void UnsignedMetaInfIndexListTest() throws Exception {
        ProcessResult pr = server.executeJavawsHeadless(TRUSTALL, "/AdditionalJarsInMetaInfIndexListUnsigned.jnlp");
        assertTrue("LoadedViaMetaInfIndexList's stdout should contain " + CORRECT_EXEC + " but did not.", pr.stdout.contains(CORRECT_EXEC));
    }
}
