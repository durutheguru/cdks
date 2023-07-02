package com.julianduru;

import com.julianduru.stacks.InfraStack;
import com.julianduru.stacks.PipelineStack;
import software.amazon.awscdk.App;
import software.amazon.awscdk.Environment;
import software.amazon.awscdk.StackProps;

public class Application {


    public static void main(final String[] args) {
        App app = new App();

        StackProps stackProps = StackProps.builder()
            .env(
                Environment.builder()
                    .account("058486276453")
                    .region("us-east-1")
                    .build()
            )
            .build();

        new PipelineStack(app, "PipelineStack", stackProps);
        new InfraStack(app, "InfraStack", stackProps);

        app.synth();
    }


}

