package patientinformationdisplayer;
import com.microsoft.azure.storage.*;
import com.microsoft.azure.storage.blob.*;
import com.microsoft.azure.storage.blob.CloudBlockBlob;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;


@Path("/get")
public class InfoGetter {

    public static final String storageConnectionString = "DefaultEndpointsProtocol=https;" + "AccountName=team19;" + "AccountKey=R41Pkh1GNKJldc+RApCn6q46trUWzlfDhIKs0axBwxgwgszOWdHoM+HiiNzZeSsMBJf/s0+fA1gUANPAAJGpSA==;EndpointSuffix=core.windows.net;";

    CloudStorageAccount storageAccount;
    CloudBlobClient blobClient = null;
    CloudBlobContainer container = null;

    @Path("/{id_of_patient}")
    @DELETE
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    @Produces(MediaType.MULTIPART_FORM_DATA)
    public void getPatientCase( @PathParam("id_of_patient") String id_of_patient)
    {
        try {
            this.storageAccount = CloudStorageAccount.parse(storageConnectionString);
            this.blobClient = storageAccount.createCloudBlobClient();
            this.container = blobClient.getContainerReference("patientdata");
            this.container.createIfNotExists(BlobContainerPublicAccessType.CONTAINER, new BlobRequestOptions(), new OperationContext());
            CloudBlockBlob blob = this.container.getBlockBlobReference(id_of_patient);
            blob.deleteIfExists();
        } catch (StorageException ex) {
            System.out.println(String.format("Error returned from the service. Http code: %d and error code: %s", ex.getHttpStatusCode(), ex.getErrorCode()));
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }

    }






    }




