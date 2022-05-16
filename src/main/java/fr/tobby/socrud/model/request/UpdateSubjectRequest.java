package fr.tobby.socrud.model.request;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import lombok.Getter;
import org.jetbrains.annotations.Nullable;

@Getter
@JsonAutoDetect
public class UpdateSubjectRequest {
    @Nullable
    private final String title;
    @Nullable
    private final String description;

    public UpdateSubjectRequest(@Nullable String title, @Nullable String description) {
        this.title = title;
        this.description = description;
    }

    public static Builder builder()
    {
        return new Builder();
    }

    public static class Builder
    {
        @Nullable
        private String title;
        @Nullable
        private String description;

        public UpdateSubjectRequest build()
        {
            return new UpdateSubjectRequest(title, description);
        }

        public Builder title(final String title)
        {
            this.title = title;
            return this;
        }

        public Builder description(final String description)
        {
            this.description = description;
            return this;
        }
    }

}
