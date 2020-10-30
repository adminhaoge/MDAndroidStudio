package com.example.design;

public class BuilderModel {
    private Builder builder;
    private BuilderModel(Builder builder) {
        this.builder = builder;
    }

    public static Builder builder(){
        return new Builder();
    }

    public static class Builder{
        private boolean isShowPosition;
        private boolean isShowCategoryName;
        private boolean isShowBrief;


        public Builder showPosition(boolean p){
            this.isShowPosition = p;
            return this;
        }

        public Builder showCategoryName(boolean c){
            this.isShowCategoryName = c;
            return this;
        }

        public Builder ShowBrief(boolean b){
            this.isShowBrief = b;
            return this;
        }

        public BuilderModel build(){
            return new BuilderModel(this);
        }

    }
}
