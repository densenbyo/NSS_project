package cz.cvut.fel.ear.lingo.content.mapper;

import cz.cvut.fel.ear.lingo.content.domain.ImageContent;
import cz.cvut.fel.ear.lingo.content.model.ImageContentModel;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ImageContentModal2ImageContentMapper {

    public ImageContent toImageContent(ImageContentModel imageContentModel) {
        if (imageContentModel == null)
            return null;
        ImageContent imageContent = new ImageContent(imageContentModel.getId(), imageContentModel.getCreatorId());
        imageContent.setURI(imageContentModel.getURI());
        return imageContent;
    }
}
