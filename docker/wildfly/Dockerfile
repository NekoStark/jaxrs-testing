FROM jboss/wildfly
ADD wait-for-it.sh .
ADD mysql /opt/jboss/wildfly/modules/system/layers/base/com/mysql/
ADD standalone.xml /opt/jboss/wildfly/standalone/configuration/
ADD restdb-ds.xml /opt/jboss/wildfly/standalone/deployments/
ADD jaxrs-testing.war /opt/jboss/wildfly/standalone/deployments/
