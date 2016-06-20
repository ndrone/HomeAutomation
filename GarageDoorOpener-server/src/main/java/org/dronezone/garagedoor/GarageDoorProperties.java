package org.dronezone.garagedoor;

import org.springframework.boot.context.properties.ConfigurationProperties;

import javax.validation.constraints.NotNull;

@ConfigurationProperties(prefix = "garage.door")
public class GarageDoorProperties
{
    @NotNull
    private String keycodeSecret;

    public String getKeycodeSecret()
    {
        return keycodeSecret;
    }

    public void setKeycodeSecret(String keycodeSecret)
    {
        this.keycodeSecret = keycodeSecret;
    }
}
