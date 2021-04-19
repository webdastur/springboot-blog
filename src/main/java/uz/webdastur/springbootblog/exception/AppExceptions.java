package uz.webdastur.springbootblog.exception;

public class AppExceptions {
    public static RuntimeException alreadyExists(String message) {
        return new EntityAlreadyExists(message);
    }

    public static RuntimeException notFound(String message) {
        return new EntityNotFound(message);
    }

    public static class EntityAlreadyExists extends RuntimeException {
        public EntityAlreadyExists(String message) {
            super(message);
        }
    }

    public static class EntityNotFound extends RuntimeException {
        public EntityNotFound(String message) {
            super(message);
        }
    }
}
