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
package org.wildfly.extras.jsf.myfaces.test.subsystem;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.wildfly.core.testrunner.WildFlyRunner;

/**
 * @author <a href="mailto:jasondlee@redhat.com">Jason Lee</a>
 */
@RunWith(WildFlyRunner.class)
public class SubsystemSanityTestCase {

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
