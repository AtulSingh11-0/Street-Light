package ashis.com.backend.Street_light.exceptions;

public class AreaCodeAlreadyExistsException extends RuntimeException{
  public AreaCodeAlreadyExistsException(String message){
    super(message);
  }
}
