Galleon Feature Packs for integrating MyFaces into WildFly and WildFly Preview
==================================================

This feature pack for WildFly provides MyFaces 4.x support for WildFly. It will allow an administrator to deploy applications
that depend upon -- or simply prefer -- MyFaces, which is configured as the default Jakarta Faces implementation once the
feature pack is installed.

The MyFaces feature pack is to be provisioned along with the WildFly Galleon feature pack.

Resources:

* [WildFly Installation Guide](https://docs.wildfly.org/27/#installation-guides)
* [Galleon documentation](https://docs.wildfly.org/galleon/)

Feature pack compatible with WildFly
============================

The Maven coordinates to use is: `org.wildfly:wildfly-myfaces-feature-pack:<version>`

Using the MyFaces feature pack
==========================

Provisioning of the MyFaces layer can be done in multiple ways according to the provisioning tooling in use.

## Provisioning using CLI tool

You can download the latest Galleon CLI tool from the Galleon GitHub project [releases](https://github.com/wildfly/galleon/releases).

You need to define a Galleon provisioning configuration file such as:

```
<?xml version="1.0" ?>
<installation xmlns="urn:jboss:galleon:provisioning:3.0">
  <feature-pack location="org.wildfly:wildfly-galleon-pack:29.0.0.Final">
    <default-configs inherit="true"/>
    <packages inherit="true"/>
  </feature-pack>
  <feature-pack location="org.wildfly:wildfly-myfaces-feature-pack:1.0.0.Final-SNAPSHOT">
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

## Provisioning using the [WildFly Maven Plugin](https://github.com/wildfly/wildfly-maven-plugin/) or the [WildFly JAR Maven plugin](https://github.com/wildfly-extras/wildfly-jar-maven-plugin/)

You need to include the MyFaces feature pack and layers in the Maven Plugin configuration. This looks like:

```
...
<feature-packs>
    <feature-pack>
        <location>org.wildfly:wildfly-galleon-pack:39.0.1.Final</location>
    </feature-pack>
    <feature-pack>
        <location>org.wildfly:wildfly-myfaces-feature-pack:2.0.3.Final</location>
    </feature-pack>
</feature-packs>
<layers>
    <layer>myfaces</layer>
</layers>
...
```
