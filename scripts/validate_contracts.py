from pathlib import Path
import yaml

checks = [
    (Path('openapi/openapi.yaml'), 'openapi', '3.1.0', ('info', 'paths', 'components')),
    (Path('asyncapi/asyncapi.yaml'), 'asyncapi', '3.0.0', ('info', 'channels', 'components')),
]

for path, version_key, expected_version, required in checks:
    data = yaml.safe_load(path.read_text(encoding='utf-8'))
    assert data[version_key] == expected_version, f'{path}: expected {version_key}={expected_version}'
    for key in required:
        assert key in data, f'{path}: missing {key}'
    print(f'{path}: BASELINE_CONTRACT_CHECK=PASS version={expected_version}')
