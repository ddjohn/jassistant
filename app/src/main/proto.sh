#!/bin/bash

PROTOC=protoc
SRC=proto

${PROTOC} --version
for FILE in $(find ${SRC} -name "*.proto")
do
	echo ${PROTOC} -I=${SRC} --java_out=java ${FILE}
	${PROTOC} -I=${SRC} --java_out=java ${FILE}
done

