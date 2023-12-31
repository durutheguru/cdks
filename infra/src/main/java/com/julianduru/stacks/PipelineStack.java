package com.julianduru.stacks;

import com.julianduru.stages.AppStage;
import software.amazon.awscdk.Environment;
import software.amazon.awscdk.Stack;
import software.amazon.awscdk.StackProps;
import software.amazon.awscdk.StageProps;
import software.amazon.awscdk.pipelines.CodePipeline;
import software.amazon.awscdk.pipelines.CodePipelineSource;
import software.amazon.awscdk.pipelines.ShellStep;
import software.constructs.Construct;

import java.util.Arrays;

/**
 * created by Julian Dumebi Duru on 02/07/2023
 */
public class PipelineStack extends Stack {


    public PipelineStack(final Construct scope, final String id) {
        this(scope, id, null);
    }


    public PipelineStack(final Construct scope, final String id, final StackProps props) {
        super(scope, id, props);

        CodePipeline pipeline = CodePipeline.Builder.create(this, "pipeline")
            .pipelineName("Pipeline")
            .synth(
                ShellStep.Builder.create("Synth")
                    .input(CodePipelineSource.gitHub("durutheguru/cdks", "main"))
                    .commands(
                        Arrays.asList(
                            "npm install -g aws-cdk",
                            "cd infra",
                            "cdk deploy --all --verbose --require-approval never"
                        )
                    )
                    .primaryOutputDirectory("infra/cdk.out")
                    .build()
            )
            .build();

        pipeline.addStage(
            new AppStage(
                this, "test",
                StageProps.builder()
                    .env(
                        Environment.builder()
                            .account("058486276453")
                            .region("us-east-1")
                            .build()
                    )
                    .build()
            )
        );
    }


}



