package com.julianduru.stacks;

import org.jetbrains.annotations.Nullable;
import software.amazon.awscdk.Stack;
import software.amazon.awscdk.StackProps;
import software.amazon.awscdk.services.lambda.Function;
import software.amazon.awscdk.services.lambda.InlineCode;
import software.amazon.awscdk.services.lambda.Runtime;
import software.constructs.Construct;

/**
 * created by Julian Dumebi Duru on 04/07/2023
 */
public class LambdaStack extends Stack {


    public LambdaStack(@Nullable Construct scope, @Nullable String id) {
        this(scope, id, null);
    }


    public LambdaStack(@Nullable Construct scope, @Nullable String id, @Nullable StackProps props) {
        super(scope, id, props);

        Function.Builder.create(this, "LambdaFunction")
            .runtime(Runtime.NODEJS_18_X)
            .handler("index.handler")
            .code(new InlineCode("exports.handler = _ => 'Hello, CDK';"))
            .build();
    }


}

