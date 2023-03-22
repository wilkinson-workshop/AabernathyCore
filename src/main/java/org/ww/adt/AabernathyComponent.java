package org.ww.adt;

public abstract class AabernathyComponent
{
    /**
     * Parent instance of Aabernathy's API.
     */
    private final AabernathyI apiInstance;

    /**
     * Bare minimum attributes and features used by all
     * adt.*.* objects.
     * @param apiInstance
     */
    public AabernathyComponent(AabernathyI apiInstance)
    {
        this.apiInstance = apiInstance;
    }

    /**
     * Returns the API instance.
     * @return the API instance.
     */
    public AabernathyI getApiInstance()
    {
        return this.apiInstance;
    }
}
