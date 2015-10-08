# str-field-governor

I thought it would be interesting to experiment with a sort-of-declarative way to provide a way to scrub or format the return value of a method by configuring the method with a strategy to the work.

TODO:
 * Work in a switch so that the formatting does not always happen.
 * Experiment with a AuthorizationAwareGovernor.
 
The idea is that a class is made "govern-able" by implementing [Governable](src/main/java/str/govern/Governable.java). Governable comes with a default implementation of govern() that will do the work of scrubbing or formatting the returned value of a method. You need to employ one or two piece of syntax to make this happen.  One way is to simple wrap the returned value in the govern() method like this:

    @Override
    public String getLastname() {
        return govern(lastname, String.class);
    }

The govern() method takes two params: the object you wish to *govern* and the type you wish to be returned.  This usage will employs a default [Governor](src/main/java/str/govern/governors/Governor.java) implementation: [NullMaskingGovernor](src/main/java/str/govern/governors/NullMaskingGovernor.java).  This Governor simply nulls out the value. 

> The Type parameter in the govern() method is to avoid the cast syntax here just because I think it is more readable this way instead of `return (String) govern(lastname)`

Annotating a method with `@Governed` without any arguments will effective do the same thing.

You can also specify the Governor you with to use for a particular method.  They will ideally be a governor for each return type, or maybe one that can do it all but that might get messy. Specifying a Governor can happen in a couple of ways:

    @Override
    @Governed(governor = StringMaskingGovernor.class)
    public String getDob() {
        return govern(dob, String.class);
    }

or 

    @Override
    @Governed(Governors.mask)
    public String getFirstname() {
        return govern(firstname, String.class);
    }

The `Governors.mask` argument above point to the [Governors enum](src/main/java/str/govern/governors/Governors.java).  (I thought this might be a convenient way to encapsulate the Governors, but I am not 100% sure is makes sense.)

Look at the classes in [src/test/java](src/test/java) to see some working examples.
 
