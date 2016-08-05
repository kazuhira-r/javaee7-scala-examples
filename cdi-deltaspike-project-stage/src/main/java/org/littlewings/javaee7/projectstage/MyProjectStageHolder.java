package org.littlewings.javaee7.projectstage;

import org.apache.deltaspike.core.api.projectstage.ProjectStageHolder;

public class MyProjectStageHolder implements ProjectStageHolder {
    public static final MyProjectStage MyProjectStage = new MyProjectStage();
}
