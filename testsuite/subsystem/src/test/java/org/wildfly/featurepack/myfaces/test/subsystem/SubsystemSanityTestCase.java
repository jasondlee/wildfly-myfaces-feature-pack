/*
 * Copyright 2023 Red Hat, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.wildfly.featurepack.myfaces.test.subsystem;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.StringAsset;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * @author <a href="mailto:kabir.khan@jboss.com">Kabir Khan</a>
 */
@RunWith(Arquillian.class)
public class SubsystemSanityTestCase {


    @Deployment
    public static WebArchive getDeployment() {
        return ShrinkWrap.create(WebArchive.class, "sanity-test.war")
                .addAsWebInfResource(new StringAsset("<beans bean-discovery-mode=\"all\"></beans>"), "beans.xml")
                .addPackage(SubsystemSanityTestCase.class.getPackage());
    }

    @Test
    public void testThatMyFacesApiIsAvailable() {
        try {
            // Pretty simple/dumb test to see if a MyFaces shared util class is available. This admittedly fragile,
            // but it makes a nice, simple smoke test
            Class.forName("org.apache.myfaces.core.api.shared.ComponentUtils");
        } catch (ClassNotFoundException e) {
            Assert.fail("MyFaces class not found.");
        }
    }

    @Test
    public void testThatMojarraIsNotAvailable() {
        try {
            // Pretty simple/dumb test to see if a MyFaces shared util class is available. This admittedly fragile,
            // but it makes a nice, simple smoke test
            Class.forName("com.sun.faces.util.Util");
            Assert.fail("Mojarra class found.");
        } catch (ClassNotFoundException e) {
            //
        }
    }
}
