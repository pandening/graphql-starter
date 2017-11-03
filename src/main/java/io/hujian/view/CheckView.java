package io.hujian.view;

import com.fasterxml.jackson.core.JsonGenerator;
import io.hujian.common.JsonFactoryHolder;
import io.hujian.model.CheckModel;

import java.io.IOException;
import java.io.OutputStream;

/**
 * Created by hujian06 on 2017/11/2.
 *
 * the check view
 */
public class CheckView {

    private CheckModel checkModel; // the check model

    public CheckView(int code, String msg) {
        this.checkModel = new CheckModel(code, msg);
    }

    public void writeView(OutputStream outputStream) throws IOException {
        try (JsonGenerator jsonFactory = JsonFactoryHolder.getJsonFactory()
                .createGenerator(outputStream)){
            jsonFactory.writeStartObject();

            jsonFactory.writeObjectFieldStart("check");

            jsonFactory.writeNumberField("code", checkModel.getCode());
            jsonFactory.writeStringField("message", checkModel.getMessage());

            jsonFactory.writeEndObject();

            jsonFactory.writeEndObject();

        }
    }

}
