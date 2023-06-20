FROM itzg/minecraft-server:java16

ENV MAIN_FOLDER /code
ENV TEMP_FOLDER /temporal/project/

RUN mkdir $MAIN_FOLDER

COPY . $MAIN_FOLDER

RUN mkdir -p $TEMP_FOLDER

RUN cp -r /code/. $TEMP_FOLDER

RUN chmod -R 777 "$TEMP_FOLDER."