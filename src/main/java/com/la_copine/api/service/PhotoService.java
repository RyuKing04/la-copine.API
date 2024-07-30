package com.la_copine.api.service;

import com.la_copine.api.dto.PhotoRequestDTO;
import com.la_copine.api.dto.PhotoResponseDTO;
import com.la_copine.api.model.Photo;
import com.la_copine.api.repository.PersonRepository;
import com.la_copine.api.repository.PhotoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PhotoService {
    @Autowired
    private PhotoRepository photoRepository;
    @Autowired
    private PersonRepository personRepository;

    public List<PhotoResponseDTO> getAllPhotos() {
        List<Photo> photos = photoRepository.findAll();
        return photos.stream().map(this::mapEntityToResponseDto).collect(Collectors.toList());
    }

    public PhotoResponseDTO getPhotoById(Long id) {
        Photo photo = photoRepository.findById(id).orElse(null);
        return photo != null ? mapEntityToResponseDto(photo) : null;
    }

    public Photo createPhoto(PhotoRequestDTO photo) {
        Photo newPhoto = mapDtoToEntity(photo);
        return photoRepository.save(newPhoto);
    }

    public Photo updatePhoto(Long id, PhotoRequestDTO photoDetails) {
        Photo photo = photoRepository.findById(id).orElse(null);
        if (photo == null) {
            return null;
        }
        return photoRepository.save(Photo.builder()
                .id(id)
                .url(photoDetails.getUrl())
                .person(personRepository.findById(photoDetails.getPersonId()).orElse(null))
                .build()
        );
    }

    public void deletePhoto(Long id) {
        photoRepository.deleteById(id);
    }

    private PhotoResponseDTO mapEntityToResponseDto(Photo photo) {
        return PhotoResponseDTO.builder()
                .id(photo.getId())
                .url(photo.getUrl())
                .personId(photo.getPerson().getId())
                .build();
    }

    private Photo mapDtoToEntity(PhotoRequestDTO photoDTO) {
        return Photo.builder()
                .url(photoDTO.getUrl())
                .person(personRepository.findById(photoDTO.getPersonId()).orElse(null))
                .build();
    }

}
