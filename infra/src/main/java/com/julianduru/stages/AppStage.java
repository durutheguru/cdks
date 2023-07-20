package com.julianduru.stages;

import com.julianduru.stacks.LambdaStack;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import software.amazon.awscdk.Stack;
import software.amazon.awscdk.Stage;
import software.amazon.awscdk.StageProps;
import software.constructs.Construct;

/**
 * created by Julian Dumebi Duru on 04/07/2023
 */
public class AppStage extends Stage {


    public AppStage(@NotNull Construct scope, @NotNull String id) {
        this(scope, id, null);
    }


    public AppStage(@NotNull Construct scope, @NotNull String id, @Nullable StageProps props) {
        super(scope, id, props);

        Stack lambdaStack = new LambdaStack(this, "LambdaStack");
    }


}
