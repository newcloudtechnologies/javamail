/*
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS HEADER.
 *
 * Copyright (c) 2009-2017 Oracle and/or its affiliates. All rights reserved.
 *
 * The contents of this file are subject to the terms of either the GNU
 * General Public License Version 2 only ("GPL") or the Common Development
 * and Distribution License("CDDL") (collectively, the "License").  You
 * may not use this file except in compliance with the License.  You can
 * obtain a copy of the License at
 * https://oss.oracle.com/licenses/CDDL+GPL-1.1
 * or LICENSE.txt.  See the License for the specific
 * language governing permissions and limitations under the License.
 *
 * When distributing the software, include this License Header Notice in each
 * file and include the License file at LICENSE.txt.
 *
 * GPL Classpath Exception:
 * Oracle designates this particular file as subject to the "Classpath"
 * exception as provided by Oracle in the GPL Version 2 section of the License
 * file that accompanied this code.
 *
 * Modifications:
 * If applicable, add the following below the License Header, with the fields
 * enclosed by brackets [] replaced by your own identifying information:
 * "Portions Copyright [year] [name of copyright owner]"
 *
 * Contributor(s):
 * If you wish your version of this file to be governed by only the CDDL or
 * only the GPL Version 2, indicate your decision by adding "[Contributor]
 * elects to include this software in this distribution under the [CDDL or GPL
 * Version 2] license."  If you don't indicate a single choice of license, a
 * recipient has the option to distribute your version of this file under
 * either the CDDL, the GPL Version 2 or to extend the choice of license to
 * its licensees as provided above.  However, if you add GPL Version 2 code
 * and therefore, elected the GPL Version 2 license, then the option applies
 * only if the new code is made subject to such option by the copyright
 * holder.
 */

package javax.mail.internet;

import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * XXX - add more tests
 */
public class ParameterListTests {

    @BeforeClass
    public static void before() {
	System.out.println("ParameterListTests");
	System.clearProperty("mail.mime.windowsfilenames");
	System.clearProperty("mail.mime.applefilenames");
    }

    /**
     * Test that backslashes are properly removed.
     */
    @Test
    public void testBackslash() throws Exception {
	System.clearProperty("mail.mime.windowsfilenames");
	ParameterList pl = new ParameterList("; filename=\"\\a\\b\\c.txt\"");
	assertEquals(pl.get("filename"), "abc.txt");
    }

    /**
     * Test that a long parameter that's been split into segments
     * is parsed correctly.
     */
    @Test
    public void testLongParse() throws Exception {
	String p0 = "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa";
	String p1 = "bbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbb";
	ParameterList pl = new ParameterList("; p*0="+p0+"; p*1="+p1);
	assertEquals(p0 + p1, pl.get("p"));
    }

    /**
     * Test that a long parameter that's set programmatically is split
     * into segments.
     */
    @Test
    public void testLongSet() throws Exception {
	String p0 = "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa";
	String p1 = "bbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbb";
	ParameterList pl = new ParameterList();
	pl.set("p", p0 + p1);
	assertEquals(p0 + p1, pl.get("p"));
	String pls = pl.toString();
	assertTrue(pls.indexOf("p*0=") >= 0);
	assertTrue(pls.indexOf("p*1=") >= 0);
    }
}
