FROM oracle/glassfish:5.0
ENV DEPLOY_DIR $GLASSFISH_HOME/glassfish/domains/domain1/autodeploy/
ENV LIB_DIR $GLASSFISH_HOME/glassfish/domains/domain1/lib/
COPY target/security-messenger/WEB-INF/lib/ ${LIB_DIR}
COPY target/security-messenger.war ${DEPLOY_DIR}/security-messenger.war 
