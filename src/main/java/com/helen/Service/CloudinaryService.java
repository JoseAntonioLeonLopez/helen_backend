package com.helen.Service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;

import net.coobird.thumbnailator.Thumbnails;

@Service
public class CloudinaryService {

    Cloudinary cloudinary;

    public CloudinaryService() {
        Map<String, String> valuesMap = new HashMap<>();
        valuesMap.put("cloud_name", "daaqce9rq");
        valuesMap.put("api_key", "582624365217812");
        valuesMap.put("api_secret", "17-GSvz6zeYH5ToCPdZTaasKqK4");
        cloudinary = new Cloudinary(valuesMap);
    }

    private File convert(MultipartFile multipartFile) throws IOException {
        File file = new File(Objects.requireNonNull(multipartFile.getOriginalFilename()));
        FileOutputStream fo = new FileOutputStream(file);
        fo.write(multipartFile.getBytes());
        fo.close();
        return file;
    }
    
    public Map<String, String> upload(MultipartFile multipartFile) throws IOException {
        // Convertir MultipartFile a File
        File file = convert(multipartFile);

        // Redimensionar y comprimir la imagen antes de subirla a Cloudinary
        Thumbnails.of(file)
                  .size(800, 600) // Tamaño máximo
                  .outputQuality(0.8) // Calidad de compresión (0.0 - 1.0)
                  .toFile(file);

        // Subir la imagen a Cloudinary
        @SuppressWarnings("unchecked")
		Map<String, String> result = cloudinary.uploader().upload(file, ObjectUtils.emptyMap());

        // Eliminar el archivo temporal
        if (!file.delete()) {
            throw new IOException("Failed to delete temporary file: " + file.getAbsolutePath());
        }

        return result;
    }

    @SuppressWarnings("unchecked")
	public Map<String, String> delete(String cloudinaryPublicationId) throws IOException {
        return cloudinary.uploader().destroy(cloudinaryPublicationId, ObjectUtils.emptyMap());
    }
}