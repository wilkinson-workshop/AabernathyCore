package org.ww.adt;

public abstract class AabernathyComponent
{
    /**
     * Parent instance of Aabernathy's API.
     */
    private static AabernathyAPI apiInstance = null;

    /**
     * Returns the API instance.
     * @return the API instance.
     */
    public static AabernathyAPI getApiInstance()
    {
        return apiInstance;
    }

    /**
     * Set the API instance.
     * @param apiInstance
     */
    public static void setApiInstance(AabernathyAPI apiInstance) { AabernathyComponent.apiInstance = apiInstance; }
}
