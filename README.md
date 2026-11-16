Galleon Feature Packs for integrating MyFaces 4.x into WildFly and WildFly Preview
==================================================

This feature-pack for WildFly provides MyFaces 4.x support for WildFly. It will allow an 
administrator to deploy applications that depend upon -- or simply prefer -- MyFaces, which 
is configured as the default Jakarta Faces implementation once the feature-pack is installed.

The MyFaces feature-pack is to be provisioned along with the WildFly Galleon feature-pack.

Resources:

* [WildFly Installation Guide](https://docs.wildfly.org/27/#installation-guides)
* [Galleon documentation](https://docs.wildfly.org/galleon/)

Feature pack compatible with WildFly
============================

The Maven coordinates to use is: `org.wildfly:wildfly-myfaces-feature-pack:<version>`

> [!NOTE]
> The `master` branch -- version 3.x -- is now targeting Jakarta EE 11 and MyFaces 4.1. Users 
> still needing EE 10 and MyFaces 4.0 support should use 2.x. 

Using the MyFaces feature pack
==========================

Provisioning of the MyFaces layer can be done in multiple ways according to the provisioning tooling in use.

## Provisioning using CLI tool

You can download the latest Galleon CLI tool from the Galleon GitHub project [releases](https://github.com/wildfly/galleon/releases).

You need to define a Galleon provisioning configuration file such as:

```
<?xml version="1.0" ?>
<installation xmlns="urn:jboss:galleon:provisioning:3.0">
  <feature-pack location="org.wildfly:wildfly-ee-galleon-pack:41.0.1.Final">
    <default-configs inherit="true"/>
    <packages inherit="true"/>
  </feature-pack>
  <feature-pack location="org.wildfly:wildfly-myfaces-feature-pack:3.0.1.Final">
    <default-configs inherit="true"/>
    <packages inherit="true"/>
  </feature-pack>
  <config model="standalone" name="standalone.xml">
    <layers>
      <!-- Base layer -->
      <include name="myfaces"/>
    </layers>
  </config>
  <options>
    <option name="optional-packages" value="passive+"/>
    <option name="jboss-fork-embedded" value="true"/>
  </options>
</installation>
```

and provision it using the following command:

```
galleon.sh provision provisioning.xml --dir=my-wildfly-server
```

## Provisioning using the [WildFly Maven Plugin](https://github.com/wildfly/wildfly-maven-plugin/) or the [WildFly JAR Maven plugin](https://github.com/wildfly/wildfly-jar-maven-plugin/)

You need to include the MyFaces feature pack and layers in the Maven Plugin configuration. This looks like:

```
...
<feature-packs>
    <feature-pack>
        <location>org.wildfly:wildfly-ee-galleon-pack:40.0.0.Final</location>
    </feature-pack>
    <feature-pack>
        <location>org.wildfly:wildfly-myfaces-feature-pack:3.0.0.Final</location>
    </feature-pack>
</feature-packs>
<layers>
    <layer>myfaces</layer>
</layers>
...
```

## Testing

Build and install the reactor artifacts before running the integration tests:

```bash
mvn clean install
```

The tests provision a standard server, a standard bootable JAR, and a WildFly Preview bootable JAR with the MyFaces feature
pack. They deploy EAR fixtures using both `all` and `annotated` CDI bean discovery. Coverage includes PrimeFaces row
expansion, full postbacks, conversation isolation, Faces converter discovery, `ui:repeat` rendering, and the server product
identity. HtmlUnit executes the rendered PrimeFaces JavaScript without requiring an installed browser or driver.

After the reactor artifacts are installed, rerun the integration class across all three server modes with:

```bash
mvn -pl testsuite/subsystem -Dtest=FacesIntegrationTestCase test
```
