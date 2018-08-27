# ServiceWithBinder

# Extending the Binder class

If your service is used only by the local application and does not need to work across processes, 
then you can implement your own Binder class that provides your client direct access to public methods 
in the service.

# Note
This works only if the client and service are in the same application and process, which is most common. For example, this would work well for a music application that needs to bind an activity to its own service that's playing music in the background.

# Here's how to set it up:

1.  In your service, create an instance of Binder that does one of the following:

    a. Contains public methods that the client can call.
    b. Returns the current Service instance, which has public methods the client can call.
    c. Returns an instance of another class hosted by the service with public methods the client can call.

2.  Return this instance of Binder from the onBind() callback method.

3.  In the client, receive the Binder from the onServiceConnected() callback method and make calls to the bound service using     the methods provided.
