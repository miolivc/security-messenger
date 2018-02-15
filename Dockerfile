FROM glassfish
COPY ./target/security-messenger.war ${DEPLOYMENT_DIR}
