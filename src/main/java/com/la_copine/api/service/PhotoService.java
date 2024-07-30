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
        Photo newPhoto = new Photo();
        mapDtoToEntity(photo, newPhoto);
        return photoRepository.save(newPhoto);
    }

    public Photo updatePhoto(Long id, Photo photoDetails) {
        Photo photo = photoRepository.findById(id).orElse(null);
        if (photo != null) {
            photo.setUrl(photoDetails.getUrl());
            photo.setPerson(photoDetails.getPerson());
            return photoRepository.save(photo);
        }
        return null;
    }

    public void deletePhoto(Long id) {
        photoRepository.deleteById(id);
    }

    private PhotoResponseDTO mapEntityToResponseDto(Photo photo) {
        PhotoResponseDTO photoResponseDTO = new PhotoResponseDTO();
        photoResponseDTO.setId(photo.getId());
        photoResponseDTO.setUrl(photo.getUrl());
        photoResponseDTO.setPersonId(photo.getPerson().getId());
        return photoResponseDTO;
    }

    private void mapDtoToEntity(PhotoRequestDTO photoDTO, Photo photo) {
        photo.setUrl(photoDTO.getUrl());
        photo.setPerson(personRepository.findById(photoDTO.getPersonId()).orElse(null));
    }

}
