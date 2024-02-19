package ArtBridge.ArtBridgelogin.service.errorMessage;

public class DuplicateMemberException extends RuntimeException {
    public DuplicateMemberException(String message) {
        super(message);
    }
}