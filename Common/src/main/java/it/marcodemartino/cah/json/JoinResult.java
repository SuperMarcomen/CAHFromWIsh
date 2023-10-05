package it.marcodemartino.cah.json;

import com.google.gson.annotations.SerializedName;

public enum JoinResult {

    @SerializedName("non_existent")
    NON_EXISTENT,

    @SerializedName("already_started")
    ALREADY_STARTED,

    @SerializedName("successful")
    SUCCESSFUL;
}
